#!/bin/sh
# Point git at the tracked hooks in .githooks/ (git does not share .git/hooks
# between clones, so this has to be run once per clone).
set -e
cd "$(dirname "$0")/.."
git config core.hooksPath .githooks
echo "core.hooksPath -> .githooks"
echo "Hooks enabled: $(ls .githooks | tr '\n' ' ')"
