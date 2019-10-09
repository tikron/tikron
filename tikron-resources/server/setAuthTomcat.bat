@echo off

set PathTomcat=c:\Program Files (x86)\Apache\apache-tomcat-8.0.33

icacls "%PathTomcat%\conf" /grant:r "Benutzer":(OI)(CI)M
icacls "%PathTomcat%\logs" /grant:r "Benutzer":(OI)(CI)M
icacls "%PathTomcat%\temp" /grant:r "Benutzer":(OI)(CI)M
icacls "%PathTomcat%\webapps" /grant:r "Benutzer":(OI)(CI)M
icacls "%PathTomcat%\work" /grant:r "Benutzer":(OI)(CI)M
icacls "%PathTomcat%\wtpwebapps" /grant:r "Benutzer":(OI)(CI)M
