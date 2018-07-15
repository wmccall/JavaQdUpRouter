#!/bin/bash
cd /home/ec2-user/raw
sudo yum -y install ant
ant clean compile jar
sudo mv scripts/qduprouter.service /etc/systemd/system/qduprouter.service
sudo systemctl daemon-reload
sudo systemctl enable qduprouter.service