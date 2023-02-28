#!/bin/bash

psql \
    -h csce-315-db.engr.tamu.edu     \
    -U csce315331_team_43_master     \
    -d csce315331_team_43            \
    -f ./sql_scripts/table_setup.sql \
    --echo-all
