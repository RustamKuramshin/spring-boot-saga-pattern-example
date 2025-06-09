#!/usr/bin/env bash

docker-compose -f docker-compose-postgres.yml up -d

docker-compose -f docker-compose-postgres.yml down