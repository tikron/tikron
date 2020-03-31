/**
 * Copyright (c) 2019 by Titus Kruse.
 */
package de.tikron.webapp.service.common;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.AddressNotFoundException;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CountryResponse;
import com.maxmind.geoip2.record.Country;

/**
 * Implementation of {@link GeoLocationService} based on MaxMind (R) open source geo IP database.
 *
 * @date 29.03.2019
 * @author Titus Kruse
 */
public class GeoLocationServiceMaxMindImpl implements GeoLocationService {

	private static Logger logger = LogManager.getLogger();
	
	@Value("classpath:GeoLite2-Country.mmdb")
	private Resource database;
	
	private DatabaseReader databaseReader;
	
	@PostConstruct
	private void init() throws IOException {
		Objects.requireNonNull(database, "Resource Geo IP database must not be null.");
		databaseReader = new DatabaseReader.Builder(database.getInputStream()).build();
		logger.info("Geo location service initialized with database [{}].", database);
	}

	@Override
	public String getCountryIsoCode(HttpServletRequest request) {
		try {
			String remoteAddress = request.getRemoteAddr();
			// remoteAddress = "185.93.182.142";
			if (remoteAddress != null && !remoteAddress.isEmpty()) {
				InetAddress ipAddress = InetAddress.getByName(remoteAddress);
				// Lookup database
				logger.debug("Geo location lookup for IPv4 address {}.", ipAddress);
				CountryResponse response = databaseReader.country(ipAddress);
				Country country = response.getCountry();
				logger.debug("Geo location lookup country result {}.", country);
				return country.getIsoCode();
			}
		} catch(AddressNotFoundException e) {
			return null;
		} catch(IOException e) {
			e.printStackTrace();
		} catch (GeoIp2Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
