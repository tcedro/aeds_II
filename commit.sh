#!/bin/bash
# Add all changes
git add .
# Commit changes with the provided message
git commit -m "$1"
# Push changes to the remote repository
git push