---
  layout: default.md
    title: "User Guide"
    pageNav: 3
---

# GuestNote User Guide

GuestNote is a **desktop app for hotel concierge, optimized for use via a  Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, GuestNote can get your guest management tasks done faster than traditional GUI apps.

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## Quick start

<box type="info">

Hey there! If you are an **advanced user** and you:
* Have Java `17` or above installed in your computer, and
* Already have GuestNote installed in your computer,

Click [here](#features) to view the features of GuestNote.

</box>

#### Installing Java and GuestNote
1. If you already have Java `17` or above installed in your computer, you can skip to step 2<br>

<box border-left-color="#3c3c3c" icon=":glyphicon-wrench:" light>
<span class="badge bg-secondary" style="font-size:1em">Windows Users: </span>
<b>Installing Java for Windows</b> <br>

* Follow these instructions to download and install Java [here](https://se-education.org/guides/tutorials/javaInstallationWindows.html). { text="1. " }
* To check your Java version on Windows, please follow these steps: { text="2. " }
    * Open Command Prompt. { text="2.1 " }
    * Type `java -version` and press Enter. { text="2.2 " }
    * Check the version of Java installed on your computer: { text="2.3 " }

<img src="images/ug/checkjavaversion_windows.jpeg" alt="Java version Windows" style="width:100%;">
</box>
<box border-left-color="#3c3c3c" icon=":glyphicon-wrench:" light>
<span class="badge bg-secondary" style="font-size:1em">Mac Users</span>
<b>Installing Java for Mac</b> <br>

* Follow these instructions to download and install Java [here](https://se-education.org/guides/tutorials/javaInstallationMac.html). { text="1. " }
* To check your Java version on Mac, please follow these steps: { text="2. " }
    * Open Terminal. { text="2.1 " }
    * Type `java --version` and press Enter. { text="2.2 " }
    * Check the version of Java installed on your computer: { text="2.3 " }

<img src="images/ug/checkjavaversion_mac.png" alt="Java version Mac" style="width:100%;">
</box>
<br>

2. Download the latest `.jar` file from [here](https://github.com/AY2425S2-CS2103T-W09-2/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your GuestNote. GuestNote will create all the files it needs in this folder.

#### Running GuestNote

1. Open a command terminal, and use the `cd` command to navigate into the folder you put the jar file in. For example, if you put the jar file in a folder named `GuestNote` on the `Desktop`, you can run the following commands:
    ```bash
    cd Desktop      # Navigate from home directory to the Desktop
    cd GuestNote    # Navigate to the folder containing the jar file
    ```
   
2. Run `java -jar guestnote.jar` command to run the application.<br>
   A GUI should appear in a few seconds. Note how the app contains some sample data.<br>
   ![firstview.png](images/ug/firstview.png)

3. If you prefer to explore GuestNote on your own, you may refer to the [Features](#features) below for details of each command. Otherwise, keep reading!

## Introducing the User Interface

**The app is divided into 3 parts:**
1. **Command Box:** This is where you type the commands.
2. **Output Box:** This is where the result of the command is shown.
3. **Guest Book:** This is where the list of guests is shown.
![labelledUI.png](images/ug/labelledUI.png)

<box type="info" header="##### Try out your first command: `help`" light>

1. Type the command `help` into the Command Box, as shown below, then press Enter. 
2. Now you can always come back to this page if you need help:
![help_command_executed.png](images/ug/help_command_executed.png) 

</box>

<box type="tip" header="##### Try out some other commands: `add`, `list`, `edit`, `check-in`, and `delete`" light>

Some example commands you can try:

<box type="default" icon=":glyphicon-plus:" background-color="#efefef" light>

**Add your first guest:** `add n/John Doe p/91234567 e/johnd@example.com r/01-01`<br>
Adds a guest named !!John Doe!! who lives in room !!#01-01!! with a unique email !!johnd@example.com!! and phone number !!91234567!!.

</box>
<box type="default" icon=":glyphicon-th-list:" background-color="#efefef" light>

**List all guests:** `list`<br>
Shows a list of all guests.

</box>
<box type="default" icon=":glyphicon-edit:" background-color="#efefef" light>

**Edit a guest:** `edit 1 +rq/Room cleaning`<br>
Edits the !!first guest!!, adding an additional request !!Room cleaning!!.

</box>
<box type="default" icon=":glyphicon-log-out:" background-color="#efefef" light>

**Check-in a guest:** `check-in 1`<br>
Checks in the !!first guest!!.

</box>
<box type="default" icon=":glyphicon-trash:" background-color="#efefef" light>

**Delete a guest:** `delete 1`<br>
Deletes the !!first guest!! from the app.

</box>

Want to learn more? Continue reading the [Features](#features) section below.

</box>
<br>



--------------------------------------------------------------------------------------------------------------------

## Features

<box type="primary" light>

#### Format Legend
Woah! Before we dive into the features, here's a quick legend to help you understand the format of the commands:

<box type="default" icon=":mif-text-fields:" background-color="#efefef" light>

**UPPER_CASE = !!Fill it in!!**<br>
Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
**Example:** `n/NAME` :mif-arrow-right-alt: `n/John Doe` :mif-done:

</box>
<box type="default" icon=":mif-data-array:" background-color="#efefef" light>

**[Square Brackets] = !!Optional!!** <br>
Items in square brackets are optional.<br>
**Example:** `n/NAME [rq/REQUEST]`<br>
:mif-arrow-right-alt: `n/John Doe` :mif-done: <br>
:mif-arrow-right-alt: `n/John Doe rq/New Pillow` :mif-done:

</box>
<box type="default" icon=":mif-more-horiz:" background-color="#efefef" light>

**Ellipsis … = !!Multiple Times!!** <br>
Items with `…` after them can be used multiple times including zero times.<br>
**Example:** `[rq/REQUEST]…`<br>
:mif-arrow-right-alt: ` ` (not used at all) :mif-done: <br>
:mif-arrow-right-alt: `rq/New Pillow` :mif-done: <br>
:mif-arrow-right-alt: `rq/New Pillow rq/Orange Juice rq/Socks` :mif-done:
</box>
<box type="warning" icon=":mif-swap-horiz:">

**!!Any Order!!: Parameters can be in any order**<br>
Example: `n/NAME `**`p/PHONE e/EMAIL`**<br>
:mif-arrow-right-alt: `n/NAME `**`p/PHONE e/EMAIL`** (same order) :mif-done: <br>
:mif-arrow-right-alt: **`e/EMAIL`**` n/NAME `**`p/PHONE`** (different order) :mif-done:

</box>
<box type="wrong" icon=":mif-close:">

**!!Copy Carefully!!: Copying across line breaks in PDF version may cause issues**<br>
If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.

</box>

</box>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a guest: `add`

Adds a guest to the guest list.

Format: `add n/NAME e/EMAIL [p/PHONE_NUMBER] r/ROOM_NUMBER [rq/REQUEST]…​`

<box type="tip" seamless>

**Tip:** 
* A guest can have any number of requests (including 0)
* A guest can be added without a phone number

</box>

Examples:
* `add n/John Doe e/johnd@example.com p/98765432 r/01-01`
* `add n/Betsy Crowe rq/Add Pillow e/betsycrowe@example.com p/1234567 r/01-01 rq/Orange Juice`

### Listing all guests : `list`

Shows a list of all guests in the guest list.

Format: `list`

### Editing a guest : `edit`

Edits an existing guest in the guest list.

Format: `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [+rq/REQUEST]…​ [-rq/REQUEST]…​`

* Edits the guest at the specified `INDEX`. The index refers to the index number shown in the displayed guest list. The index **must be a positive integer and must be within the number of guests displayed** 1, 2, 3, …​ 
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing requests:
  * an add request input will be added to the back of the current request list for the guest.
  * a remove request input will cause the positions for all remaining requests will be updated.
* If the edit results in a duplicate guest in the guest list, the command is not allowed. 

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com ` Edits the phone number and email address of the 1st guest to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower -rq/Extra toothbrush` Edits the name of the 2nd guest to be `Betsy Crower` and removes the existing request `Extra toothbrush`.
*  `edit 3 e/chloeng@example.com` Will not be allowed when there is an existing guest with the email `chloeng@example.com` in the guest list. 

### Locating guests by name: `find`

Finds guests whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Guests matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Deleting a guest : `delete`

Deletes the specified guest from the guest list.

Format: `delete INDEX`

* Deletes the guest at the specified `INDEX`.
* The index refers to the index number shown in the displayed guest list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd guest in the guest list.
* `find Betsy` followed by `delete 1` deletes the 1st guest in the results of the `find` command.

### Clearing all entries : `clear`

Clears all entries from the guest list.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

GuestNote data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

GuestNote data are saved automatically as a JSON file `[JAR file location]/data/guestnote.json`. Advanced users are welcome to update data directly by editing that data file.

<box type="warning" seamless>

**Caution:**
If your changes to the data file makes its format invalid, GuestNote will discard all data and start with an empty data file at the next run.  Hence, it is recommended to take a backup of the file before editing it.<br>
Furthermore, certain edits can cause the GuestNote to behave in unexpected ways (e.g., if a value entered is outside the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</box>

### Check-In a guest : `check-in`

Check-in a guest to the hotel.

Format: `check-in INDEX`

* Check-in the guest at the specified `INDEX`.
* If the guest is already checked-in, the command will not have any effect and an exception will show up in the app.

### Check-Out a guest : `check-out`

Check-out a guest from the hotel.

Format: `check-out INDEX`

* Check-out the guest at the specified `INDEX`.
* If the guest is already checked-out, the command will not have any effect and an exception will show up in the app.
* If the guest is not checked in yet, the command will not have any effect and an exception will show up in the app.

### Extended Find `[coming in v1.4]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous GuestNote home folder.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.
2. **If you minimize the Help Window** and then run the `help` command (or use the `Help` menu, or the keyboard shortcut `F1`) again, the original Help Window will remain minimized, and no new Help Window will appear. The remedy is to manually restore the minimized Help Window.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action     | Format, Examples
-----------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------
**Add**    | `add n/NAME p/PHONE_NUMBER e/EMAIL r/ROOM_NUMBER [rq/REQUEST]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com r/01-01 rq/Add Pillow rq/Orange Juice`
**Clear**  | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit**   | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [rq/REQUEST]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find**   | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List**   | `list`
**Help**   | `help`
**Exit**   | `exit`
**Check-In** | `check-in INDEX`<br> e.g., `check-in 1`
**Check-Out** | `check-out INDEX`<br> e.g., `check-out 1`
