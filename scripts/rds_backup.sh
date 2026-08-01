#!/bin/bash

set -e

TIMESTAMP=$(date +"%Y-%m-%d_%H-%M-%S")

DB_NAME="devopsdb"

BACKUP_DIR="$HOME/backups"

LOG_FILE="$HOME/logs/rds_backup.log"

S3_BUCKET="s3://thavasurya-devops-capstone-backups-251213420272/database"

BACKUP_FILE="$BACKUP_DIR/${DB_NAME}_${TIMESTAMP}.sql"

echo "======================================" >> "$LOG_FILE"
echo "$(date) - Backup started" >> "$LOG_FILE"

mkdir -p "$BACKUP_DIR"

mysqldump \
  --defaults-file=$HOME/.mysql/.my.cnf \
  --single-transaction \
  --set-gtid-purged=OFF \
  "$DB_NAME" > "$BACKUP_FILE"

gzip "$BACKUP_FILE"

aws s3 cp \
"$BACKUP_FILE.gz" \
"$S3_BUCKET/"

find "$BACKUP_DIR" \
-name "*.gz" \
-type f \
-mtime +7 \
-delete

echo "$(date) - Backup completed successfully" >> "$LOG_FILE"
