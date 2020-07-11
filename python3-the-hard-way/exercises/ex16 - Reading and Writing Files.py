# File methods:
# close() - Closes the file...
# read() - Read the conents of the file
# readline() - Read a line
# trunacte() - Empties the file
# write('foo') - Writes to the file
# seek(0) - Seek to the start of the file stream

from sys import argv

script, fileName = argv

print(f"Deleting {fileName}")
print("Continue?")

input("?")

print("Opening the file...")
fileObject = open(fileName, "w")

print("Truncating the file.")
#fileObject.truncate()

line1 = input("line 1: ")
line2 = input("line 2: ")
line3 = input("line 3: ")

print("Writing those 3 lines to the file.")

fileObject.write(line1)
fileObject.write("\n")
fileObject.write(line2)
fileObject.write("\n")
fileObject.write(line3)
fileObject.write("\n")

print("Closing file.")
fileObject.close()