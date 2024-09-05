# Xml Parser
## Command line interface design
### Requirements
- Generic
  - Open a file, load it into memory, and close file
  - Close file - clear file memory and restrict commands to only open
  - Save - save content into same filepath
  - Save as - save content into new filepath
  - Help - display usage info
  - Exit
- Project specific
  - Print - pretty print the loaded XML file
  - select <id> <key> - print value of attribute by id & key
  - set <id> <key> <value> - self explanatory
  - children <id> <n> - print n-th children of element with id
  - text <id> - print the text of the id
  - delete <id> <key> delete attribute of element by id
  - newchild <id> - adds new empty attribute to element
  - xpath <id> <XPath> - Xpath 2.0 query support

Parser is design is as:
1. Load xml file into memory
2. Validate if the xml is valid
3. deserialize xml to a tree
4. do operations
5. on save, serialize back to xml