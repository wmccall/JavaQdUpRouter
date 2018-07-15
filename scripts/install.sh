#!/bin/bash
cd /home/ec2-user/raw
sudo yum -y install ant
ant clean compile jar