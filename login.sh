#!/bin/bash

export PGPASSWORD=password43

psql \
    -h csce-315-db.engr.tamu.edu     \
    -U csce315331_team_43_master     \
    -d csce315331_team_43
