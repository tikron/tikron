openssl req -new -out tikron.de.csr -keyout tikron.de.pem
openssl rsa -in tikron.de.pem -out tikron.de.key
openssl x509 -in tikron.de.csr -out tikron.de.crt -req -signkey tikron.de.key -days 3650
openssl x509 -in tikron.de.crt -out tikron.de.der.crt -outform DER

