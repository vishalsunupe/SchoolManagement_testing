# 🚀 Daily Git Commands Cheat Sheet

A comprehensive, categorized guide to essential Git commands used in daily software development workflows.

---
```bash
## 📋 Table of Contents
1. [Initial Setup & Configuration](#1-initial-setup--configuration)
2. [Creating & Cloning Repositories](#2-creating--cloning-repositories)
3. [Daily Development Workflow](#3-daily-development-workflow)
4. [Branch Management](#4-branch-management)
5. [Remote Repositories](#5-remote-repositories)
6. [Merging & Rebasing](#6-merging--rebasing)
7. [Stashing (Temporary Storage)](#7-stashing-temporary-storage)
8. [Inspecting History & Diffs](#8-inspecting-history--diffs)
9. [Undoing & Fixing Mistakes](#9-undoing--fixing-mistakes)
10. [Tagging & Releases](#10-tagging--releases)
11. [Useful Git Aliases & Tips](#11-useful-git-aliases--tips)
```
---

## 1. Initial Setup & Configuration

Configure Git with your identity and global preferences (run once per system).

```bash
https://github.com/vishalsunupe/SchoolManagement_testing.git
# Set your name and email globally
git config --global user.name "Your Name"
git config --global user.email "your.email@example.com"

# Set default initial branch name to 'main'
git config --global init.defaultBranch main

# Enable colored output in terminal
git config --global color.ui auto

# Handle line endings (Windows: true | Mac/Linux: input)
git config --global core.autocrlf true

# View all current configurations
git config --list --show-origin
```

---

## 2. Creating & Cloning Repositories

```bash
# Initialize a new Git repository in the current directory
git init

# Initialize with a specific branch name
git init -b main

# Clone an existing repository from GitHub/GitLab/Bitbucket
git clone https://github.com/username/repository.git

# Clone into a specific folder name
git clone https://github.com/username/repository.git my-folder-name
```

---

## 3. Daily Development Workflow

The core cycle: **Edit → Stage → Commit → Push**

```bash
# Check status of working directory & staged files
git status

# Check status in compact/short format
git status -s

# Stage specific file(s)
git add filename.java

# Stage all tracked and untracked changes in the current directory
git add .

# Interactive staging (hunk-by-hunk review)
git add -p

# Unstage a file (keep local changes in working directory)
git restore --staged filename.java

# Commit staged changes with a concise message
git commit -m "feat: add user authentication module"

# Stage all modified/deleted tracked files and commit in one step
git commit -am "fix: resolve login validation bug"

# Amend / modify the previous commit (change message or add forgotten files)
git commit --amend -m "feat: add user authentication module with unit tests"
```

---

## 4. Branch Management

Work on isolated features, bug fixes, or experiments.

```bash
# List local branches (* indicates current branch)
git branch

# List all local and remote-tracking branches
git branch -a

# Create a new branch
git branch feature/login-page

# Switch to an existing branch
git switch feature/login-page
# (Alternative legacy command): git checkout feature/login-page

# Create AND switch to a new branch in one command
git switch -c feature/login-page
# (Alternative legacy command): git checkout -b feature/login-page

# Rename current branch
git branch -m new-branch-name

# Delete a local branch (safe delete - checks if merged)
git branch -d feature/login-page

# Force delete a local branch (unmerged changes will be lost)
git branch -D feature/login-page

# Delete a remote branch
git push origin --delete feature/login-page
```

---

## 5. Remote Repositories

Collaborate with remote platforms (GitHub, GitLab, Azure DevOps).

```bash
# List configured remote repositories with URLs
git remote -v

# Add a remote repository link
git remote add origin https://github.com/username/repository.git

# Change remote repository URL
git remote set-url origin https://github.com/username/new-repository.git

# Fetch latest changes from remote (does NOT merge into working code)
git fetch origin

# Fetch and automatically prune deleted remote branches locally
git fetch --prune

# Pull latest changes from remote and merge into current branch
git pull origin main

# Pull using rebase instead of merge (cleaner linear history)
git pull --rebase origin main

# Push current branch to remote for the first time (-u sets upstream link)
git push -u origin feature/login-page

# Push changes to tracked remote branch
git push

# Force push safely (verifies remote hasn't changed unexpectedly)
git push --force-with-lease
```

---

## 6. Merging & Rebasing

Combine changes from one branch into another.

```bash
# --- MERGING ---
# First switch to target branch (e.g. main)
git switch main

# Merge feature branch into current branch (creates merge commit)
git merge feature/login-page

# Merge with no fast-forward (always creates explicit merge commit)
git merge --no-ff feature/login-page

# Abort a merge in case of conflicts
git merge --abort

# --- REBASING ---
# Rebase current feature branch onto main (re-applies commits on top of main)
git switch feature/login-page
git rebase main

# Continue rebase after resolving manual conflicts
git rebase --continue

# Abort rebase operation
git rebase --abort

# Interactive rebase last N commits (squash, edit, reorder commits)
git rebase -i HEAD~3
```

---

## 7. Stashing (Temporary Storage)

Temporarily save uncommitted work to work on something else clean.

```bash
# Save modified & staged changes to stash with optional description
git stash push -m "WIP: login page UI work"

# Stash including untracked files
git stash -u

# View list of all stashed changes
git stash list

# Apply latest stash and remove it from stash list
git stash pop

# Apply a specific stash without removing it from list
git stash apply stash@{0}

# Delete the most recent stash
git stash drop

# Delete all stashes
git stash clear
```

---

## 8. Inspecting History & Diffs

```bash
# View commit history
git log

# Compact single-line log format
git log --oneline

# Graphical commit graph showing branches and merges
git log --oneline --graph --all --decorate

# Show last N commits
git log -n 5

# Show commits made by a specific author
git log --author="Vishal"

# Show changes introduced in a specific commit
git show <commit-hash>

# Compare working directory changes with staged area
git diff

# Compare staged changes with last commit
git diff --staged

# Compare differences between two branches
git diff main..feature/login-page

# Show line-by-line file modification history and authorship
git blame filename.java
```

---

## 9. Undoing & Fixing Mistakes

```bash
# Discard uncommitted local changes in a specific file
git restore filename.java

# Discard all local changes in current directory
git restore .

# Revert a published commit (creates a new commit that undoes changes safely)
git revert <commit-hash>

# Soft Reset: Move HEAD back N commits, keep changes staged
git reset --soft HEAD~1

# Mixed Reset (default): Move HEAD back N commits, keep changes in working folder unstaged
git reset HEAD~1

# Hard Reset: DISCARD all local changes and commits back to specified state (DANGEROUS)
git reset --hard HEAD~1

# Reset current branch to match remote branch state exactly
git reset --hard origin/main

# Clean untracked files and directories (preview first with -n)
git clean -nd              # Dry-run preview
git clean -fd              # Force remove untracked files & folders

# Cherry-pick a specific commit from another branch into current branch
git cherry-pick <commit-hash>

# View emergency log of HEAD movements (recover lost commits or branches!)
git reflog
```

---

## 10. Tagging & Releases

Mark specific release milestones in repository history.

```bash
# Create an annotated tag for releases
git tag -a v1.0.0 -m "Release version 1.0.0"

# List all tags
git tag

# Push a specific tag to remote
git push origin v1.0.0

# Push all local tags to remote
git push origin --tags

# Delete a local tag
git tag -d v1.0.0

# Delete a remote tag
git push origin --delete v1.0.0
```

---

## 11. Useful Git Aliases & Tips

Set up short shortcuts in your global `.gitconfig` to save time typing.

```bash
# Setup handy aliases
git config --global alias.co checkout
git config --global alias.sw switch
git config --global alias.br branch
git config --global alias.st status
git config --global alias.cm "commit -m"
git config --global alias.lg "log --oneline --graph --all --decorate"

# Usage after alias setup:
# git st        -> git status
# git sw main   -> git switch main
# git lg        -> git log --oneline --graph --all --decorate
```

---

### 💡 Daily Best Practices Quick Summary
1. **Pull frequently** before creating new branches or pushing code (`git pull --rebase`).
2. **Commit often with clear messages** following Conventional Commits (`feat:`, `fix:`, `docs:`, `refactor:`).
3. **Never force push directly to `main` or `master`**.
4. **Use feature branches** for every independent task or bug fix.
5. **Check `git status` and `git diff`** before staging and committing.
