echo "Downloading Sealights Agents..."
wget -nv https://agents.sealights.co/sealights-java/sealights-java-latest.zip
unzip -o sealights-java-latest.zip
echo "Sealights agent version used is:" `cat sealights-java-version.txt`
rm sealights-java-latest.zip sealights-java-version.txt
