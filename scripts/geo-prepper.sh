#!/bin/bash

#############################################################################
#                                                                           #
#   It is not necessary to run this script for running this application,    #
#   as I already ran it for preparing the data.                             #
#                                                                           #
#   I put the script here only to show you how the following files were     #
#   created:                                                                #
#                                                                           #
#     - src/main/resources/US-CA-places.zip                                 #
#     - src/main/resources/US-CA-admin1-codes.txt                           #
#                                                                           #
#   But the script is well-tested and therefore you're welcome to run it    #
#   if you want to.                                                         #
#                                                                           #
#   It took me about 10 minutes to download, format, and compress the data  #
#   and your time may vary, depending on your internet speed.               #
#                                                                           #
#                                                                           #
#                                   Jiayun Han                              #
#                                                                           #
#                                   August 22, 2020 at 17:15                #
#                                                                           #
#                                   Montreal                                #
#                                                                           #
#                                                                           #
#############################################################################

dest_dir='../src/main/resources/'

echo "Downloading allCountries.zip, which will take quite a while ..."
wget http://download.geonames.org/export/dump/allCountries.zip
echo "Done"

echo "Downloading admin1 codes ..."
wget http://download.geonames.org/export/dump/admin1CodesASCII.txt
echo "Done"

echo "Unzipping allCountries.zip ..."
unzip allCountries.zip
echo "Done"

# $2=the name of the place, $5=latitude, $6=longitude, $9=country code, $11=state/province code
#
echo "Preparing US and CA names, which will take a quile ..."
awk -F'\t' 'BEGIN {OFS="\t";} $9 == "CA" || $9 == "US" { print $2, $5, $6, $9, $11}' \
     allCountries.txt > ${dest_dir}/US-CA-places.txt
echo "Done"

echo "Preparing US and CA admin codes ..."
awk -F'\t' 'BEGIN {OFS="\t";} $1 ~ /^(US|CA)/ { print $1, $2}' admin1CodesASCII.txt \
     | sed -e 's/US\.//g' | sed -e 's/CA\.//g' > ${dest_dir}/US-CA-admin1-codes.txt
echo "Done"

rm allCountries.zip allCountries.txt admin1CodesASCII.txt
echo "Deleted allCountries.zip, allCountries.txt, admin1CodesASCII.txt"

echo "Zipping US-CA-places.txt"
cd ${dest_dir}
zip US-CA-places.zip US-CA-places.txt
echo "Done"

rm US-CA-places.txt
echo "All unused files are deleted."


