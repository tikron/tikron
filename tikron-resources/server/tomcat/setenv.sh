#
# Custom Tomcat JVM runtime arguments
#

# Heap space
#CATALINA_OPTS="-Xms512m -Xmx1024m"

# Default time zone = Europe/Berlin
CATALINA_OPTS="$CATALINA_OPTS -Duser.timezone=Europe/Berlin"

# AWT image generation
#CATALINA_OPTS="$CATALINA_OPTS -Djava.awt.headless=true"

# Treat JSF empty input fields as null
#CATALINA_OPTS="$CATALINA_OPTS -Dorg.apache.el.parser.COERCE_TO_ZERO=false"

# Set aspectjweaver.jar to enable Spring POJO bean configuration (ImageServlet.java)
#CATALINA_OPTS="$CATALINA_OPTS -javaagent:/opt/apache-tomcat/lib/aspectjweaver.jar"

# Logback configuration file location
CATALINA_OPTS="$CATALINA_OPTS -Dlogback.configurationFile=/var/lib/tomcat9/conf/logback.xml"

# Tikron Web Applications configuration file location. Referenced by Spring application-context.xml
CATALINA_OPTS="$CATALINA_OPTS -Dtikron.properties=file:/var/opt/tikron/tikron.properties"

export CATALINA_OPTS="$CATALINA_OPTS"
