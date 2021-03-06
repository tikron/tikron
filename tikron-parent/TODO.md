
# Tikron

- Subdomains images.tikron.de usw. auflösen.
- Images über attachment Tabelle referenzieren.
- Images wirklich in Verzeichnis je Bereich/Kategorie speichern (Problem Verschieben zwischen Kategorien)?
- Service-Layer in separates Projekt auslagern und von webapp/webman gemeinsam nutzen.


# Tikron Persistence

- hibernate.dialect in JPA persistence.xml wird nicht mehr erkannt. Workaround über Spring persistence-context.xml ist doof. 
- WARN: HHH020100: The Ehcache second-level cache provider for Hibernate is deprecated.  See https://hibernate.atlassian.net/browse/HHH-12441 for details.
- WARN: HHH90001006: Missing cache[de.tikron.persistence.model.gallery.Picture] was created on-the-fly. The created cache will use a provider-specific default configuration: make sure you defined one. You can disable this warning by setting 'hibernate.cache.ehcache.missing_cache_strategy' to 'create'.
- Statt Album- und Picture Bilder getrennt zu speichern, diese in Attachment zusammenführen und darauf referenzieren.
- CleanUp zum Löschen nicht mehr referenzierte Bilder (Attachments) bauen.
- @ManyToOne mit Fetch.LAZY versehen? Führt ansonsten zu großen SQL-Statements z.B. bei getComments().size()
- Annotaionen @Index hinzufügen?
- Statt visible im DAO zu filtern, dies im Service Layer per Java 8 Collection:removeIf().
- Tabellen Comment und Picture... über Relationstabelle referenzieren.
- Zum Laden des Menüs Projections (DTOs) verwenden. http://blog.arnoldgalovics.com/2017/03/14/using-projections-in-your-data-access-layer/ -> Geht nicht wegen OneToMany Catalog:Category.


# Tikron Manager

- Würde gerne von JSF auf AngualarJS umstellen.


# Tikron WebApp

- Statt useraction.log in DB mittels activity table o.ä. persistieren.
- Auf HttpRequestMethodNotSupportedException reagieren (Bsp. https://www.baeldung.com/global-error-handler-in-a-spring-rest-api)
- Auf inline javascript verzichten (https://stackoverflow.com/questions/23740548/how-do-i-pass-variables-and-data-from-php-to-javascript, https://infosec.mozilla.org/guidelines/web_security#content-security-policy)
- Für Pager statt komplette EntityBeans kleine POJOs verwenden, die per JPQL-Konstruktor erstellt werden ("Java Persistence 2", Seite 202)
- buildCanonicalUrl() Objektorientiert realisieren.
- Videos mit "Video for everybody" (http://camendesign.com/code/video_for_everybody) anzeigen.
- Bilder lazy laden und Abmessungen entsprechend view port wählen.
- Bilder mit CSS max-width/max-height formattieren.
- Alternative AntiSamy suchen.
- Evtl. Google ReCaptcha verwenden (springinpractice.com/2008/03/13/how-to-recaptcha-your-java-application)
