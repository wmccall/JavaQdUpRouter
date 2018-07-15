#!/bin/bash
cd /home/ec2-user/raw
tmux new-session -d -s router
tmux new-window -t router:1 -n 'router1'
tmux send -t router.0 "ant run" ENTER