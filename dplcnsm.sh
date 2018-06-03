#! /bin/bash
set -e
app_name=$(find . -name "consumer*.jar")
echo "worker: java -jar $app_name" > Procfile
heroku deploy:jar $app_name -a wtxgsw