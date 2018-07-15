#!/bin/bash
cd /home/ec2-user/raw
sudo yum -y install ant
mkdir dep
cd dep
mkdir LIBEVENT
cd LIBEVENT
curl -OL https://github.com/downloads/libevent/libevent/libevent-2.0.21-stable.tar.gz
sudo tar -xvzf libevent-2.0.21-stable.tar.gz
cd libevent-2.0.21-stable
sudo ./configure --prefix=/usr/local
sudo make
sudo make install
cd ../..
mkdir TMUX
cd TMUX
curl -OL https://github.com/tmux/tmux/releases/download/2.0/tmux-2.0.tar.gz
sudo tar -xvzf tmux-2.0.tar.gz
cd tmux-2.0
sudo LDFLAGS="-L/usr/local/lib -Wl,-rpath=/usr/local/lib" ./configure --prefix=/usr/local
sudo make
sudo make install
cd ../../..