#!/bin/bash
set -e

mongo --host localhost --port 27017 \
  --username "${MONGO_INITDB_ROOT_USERNAME}" \
  --password "${MONGO_INITDB_ROOT_PASSWORD}" \
  --authenticationDatabase admin <<EOF
db.getSiblingDB("admin").createUser({
    user: "${MONGO_APP_USER}",
    pwd: "${MONGO_APP_PASSWORD}",
    roles: [
        { role: "readWrite", db: "${MONGO_APP_DB}" }
    ]
});
EOF
