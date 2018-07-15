#!/bin/bash
cd /home/ec2-user/raw
sudo yum -y install ant gcc tmux
ant clean compile jar