## Note: 
Documentation: https://docs.google.com/document/d/1vnRDz4gRudhiMhv6pFvyZzsi-rFzodcQ0pyne1OTMyA/edit?usp=sharing



Video linking: https://www.loom.com/share/b4e233ae56eb408f9c79269f85ddab0a?sid=418be7b5-73eb-467b-9fda-c65c7f899adb

# SocialMediaChecker

## Overview

The `SocialMediaChecker` is a Java-based tool designed to check the activity of a company's social media pages on LinkedIn and Twitter. The tool takes a company name as input and verifies whether the company has recent activity on their LinkedIn and Twitter pages.

## Features

- **LinkedIn Check**: Verifies if a LinkedIn profile for the company exists and checks if there is recent activity.
- **Twitter Check**: Verifies if a Twitter profile for the company exists and checks if there is recent activity.

## Prerequisites

- **Java Development Kit (JDK)**: Ensure that you have JDK 11 or higher installed on your system. You can download it from [Oracle's official website](https://www.oracle.com/java/technologies/javase-downloads.html).

- **Selenium WebDriver**: This tool requires ChromeDriver for Selenium to interact with web pages. Ensure that you have the ChromeDriver executable available. Download it from [ChromeDriver's official site](https://sites.google.com/chromium.org/driver/).

- **Maven**: Ensure that Maven is installed for managing dependencies and building the project. You can download it from [Maven's official site](https://maven.apache.org/download.cgi).

## Setup

### Step 1: Store ChromeDriver

1. Download the ChromeDriver executable from [ChromeDriver's official site](https://sites.google.com/chromium.org/driver/).

2. Place the `chromedriver` executable in a directory on your system. For example, you can place it in `C:\chromedriver` on Windows or `/usr/local/bin` on macOS/Linux.

### Step 2: Update Path Variable

- **Windows**:
  1. Open the Start Menu and search for "Environment Variables."
  2. Click on "Edit the system environment variables."
  3. In the System Properties window, click on the "Environment Variables" button.
  4. In the Environment Variables window, find the "Path" variable in the "System variables" section and click "Edit."
  5. Add the path to the directory where you stored `chromedriver` (e.g., `C:\chromedriver`).
  6. Click "OK" to close all the windows.

- **macOS/Linux**:
  1. Open a terminal.
  2. Edit your shell configuration file (e.g., `~/.bashrc`, `~/.zshrc`, or `~/.profile`) and add the following line:
     ```bash
     export PATH=$PATH:/path/to/chromedriver
     ```
     Replace `/path/to/chromedriver` with the directory where you stored `chromedriver`.
  3. Save the file and reload the shell configuration with:
     ```bash
     source ~/.bashrc   # or source ~/.zshrc or source ~/.profile
     ```

## How to Use

### Step 1: Clone the Repository

Clone the repository containing the `SocialMediaChecker` project:

```bash
git clone <repository_url>
cd <repository_directory>
```

Replace `<repository_url>` with the URL of the GitHub repository and `<repository_directory>` with the directory where the repository is cloned.

### Step 2: Compile and Build the Project

Navigate to the project directory and use Maven to build the project:

```bash
mvn clean package
```

This command will compile the code, resolve dependencies, and package the application into an executable JAR file.

### Step 3: Run the Tool

After building the project, you can run the tool with the following command:

```bash
java -cp target/social-media-checker-1.0-SNAPSHOT.jar SocialMediaChecker <companyName>
```

Replace `<companyName>` with the actual name of the company you want to check. For example:

```bash
java -cp target/social-media-checker-1.0-SNAPSHOT.jar SocialMediaChecker namekart
```

### Example Output

```plaintext
Checking LinkedIn URL: https://www.linkedin.com/company/namekart/posts/
LinkedIn post date: 2w ago
Company is active on LinkedIn.
Valid Twitter URL found: https://twitter.com/namekart
Twitter post datetime: 2024-09-16T15:23:00Z
Company is active on Twitter.
```

If no valid profile or recent activity is found, you will see:

```plaintext
No valid LinkedIn profile found for namekart
No valid Twitter profile found for namekart
```

### Step 4: Interpret the Results

The tool will output the existence and recent activity of the company's LinkedIn and Twitter pages. It will print "Company is active on [Platform]." if recent activity is detected or "Company has a [Platform] profile but is not active." if no recent activity is found.

## Notes

- This tool uses Selenium WebDriver to interact with web pages and scrape information.
- Ensure that the paths and URLs in the code are correct and accessible.

## License

This project is open-source and available under the [MIT License](LICENSE).

## Additional Information

- **Error Handling**: If there's an issue during the check, the tool will output an error message explaining what went wrong.
- **Future Enhancements**: Potential improvements could include checking additional social media platforms, refining date parsing, and handling more dynamic web content.
