@echo off

set PathApache=c:\Program Files (x86)\Apache\Apache24

icacls "%PathApache%\conf" /grant:r "Benutzer":(OI)(CI)M
icacls "%PathApache%\htdocs" /grant:r "Benutzer":(OI)(CI)M
icacls "%PathApache%\logs" /grant:r "Benutzer":(OI)(CI)M
