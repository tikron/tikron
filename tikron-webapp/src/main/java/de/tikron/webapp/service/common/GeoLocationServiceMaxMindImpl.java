/**
 * Copyright (c) 2019 by Titus Kruse.
 */
package de.tikron.webapp.service.common;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Objects;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.AddressNotFoundException;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CountryResponse;
import com.maxmind.geoip2.record.Country;

/**
 * Implementation of {@link GeoLocationService} based on MaxMind (R) open source geo IP database.
 *
 * @author Titus Kruse
 * @since 29.03.2019
 */
@Service("geoLocationService")
public class GeoLocationServiceMaxMindImpl implements GeoLocationService {

	private static Logger logger = LoggerFactory.getLogger(GeoLocationServiceMaxMindImpl.class);
	
	@Value("file:${tikron.geo-location.maxmind.country-db}")
	private Resource database;
	
	private DatabaseReader databaseReader;
	
	@PostConstruct
	private void init() throws IOException {
		Objects.requireNonNull(database, "Resource Geo IP database must not be null.");
		databaseReader = new DatabaseReader.Builder(database.getInputStream()).build();
		logger.info("Initialized MaxMind Geo Location Service with database [{}].", database);
	}

	@Override
	public String getCountryIsoCode(InetAddress ipAddress) throws GeoLocationServiceException {
		Objects.requireNonNull(ipAddress, "Parameter ipAddress must nor be null");
		try {
			logger.debug("Geo location lookup for IPv4 address {}.", ipAddress);
			CountryResponse response = databaseReader.country(ipAddress);
			Country country = response.getCountry();
			logger.debug("Geo location lookup country result {}.", country);
			return country.getIsoCode();
		} catch(AddressNotFoundException e) {
			return null;
		} catch(IOException e) {
			throw new GeoLocationServiceException(e);
		} catch (GeoIp2Exception e) {
			throw new GeoLocationServiceException(e);
		}
	}

	@Override
	public String getCountryIsoCode(String ipAddress) throws GeoLocationServiceException {
		try {
			if (ipAddress != null && !ipAddress.isEmpty()) {
				InetAddress inetAddress = InetAddress.getByName(ipAddress);
				return getCountryIsoCode(inetAddress);
			}
			return null;
		} catch (UnknownHostException e) {
			throw new GeoLocationServiceException(e);
		}
	}
}
