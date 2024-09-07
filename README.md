# SocialMediaChecker

## Overview
The `SocialMediaChecker` is a Java-based tool designed to check the existence of a company's social media pages on Twitter and Instagram. The tool takes a company name as input and verifies whether the corresponding social media pages exist.

## Features
- **Twitter Check**: Verifies if a Twitter account exists for the given company.
- **Instagram Check**: Verifies if an Instagram account exists for the given company.
- **HTTP Status Code**: The tool checks the HTTP status code to determine the existence of the social media page.

## Prerequisites
- **Java Development Kit (JDK)**: Ensure that you have JDK installed on your system. You can download it from [Oracle's official website](https://www.oracle.com/java/technologies/javase-downloads.html).

## How to Use

### Step 1: Compile the Code
Before running the tool, you need to compile the Java source file.

Open a terminal or command prompt and navigate to the directory where the `SocialMediaChecker.java` file is located.

Run the following command to compile the code:
```bash
javac SocialMediaChecker.java
```

### Step 2: Run the Tool
After successful compilation, you can run the tool with the following command:

```bash
java SocialMediaChecker <companyName>
```

Replace `<companyName>` with the actual name of the company you want to check. For example:

```bash
java SocialMediaChecker namekart
```

### Example Output

Checking social media for company: namekart
Twitter: Exists for namekart
Instagram: Exists for namekart

If the company does not have a page on either platform, you will see:

Checking social media for company: namekart
Twitter: Not found
Instagram: Not found


### Step 3: Interpret the Results
The tool will output the existence of the company's Twitter and Instagram pages. It will print "Exists" if the page is found or "Not found" if the page does not exist.

## Notes
- This tool uses the basic HTTP GET request to check the existence of a page. If the page exists, it assumes that the company has a social media presence on that platform.
- The tool does not verify the content or activity of the pages.
  
## License
This project is open-source and available under the [MIT License](LICENSE).


## Additional Information
- **Error Handling**: If there's an issue during the check, the tool will output an error message explaining what went wrong.
- **Future Enhancements**: Potential improvements could include checking the activity of the pages, handling redirects, and expanding to more social media platforms.

---
