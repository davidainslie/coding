# Setup

Apologies I shall only cover **Mac** - One day I may include Linux and Windows.

Install [Homebrew](https://brew.sh) for easy package management on Mac:

```shell
ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"
```

Installation essentials:

```shell
brew update
brew install --cask temurin
brew install scala
brew install sbt
```

Using the latest version of Java at this time, 18 (temurin):
```shell
jenv add /Library/Java/JavaVirtualMachines/temurin-18.jdk/Contents/Home
```

We can sanity check our project with [sbt-dependency-check](https://github.com/albuch/sbt-dependency-check):
```shell
sbt dependencyCheck
```

And you should double check for any secrets you may have checked into Git:
```shell
git secrets scan
```