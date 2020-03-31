echo "Tikron Gallery-Import"
echo ""
java -Xmx128m -jar ../galleryimport.jar "/var/opt/ibase/archive/gallery/$1/" "$1" "localhost:3306" tikron tikron "Wuik12Dofe"
 