from sys import argv
from os import path

script, sourceFilePath, destinationFilePath = argv

print(f"Copying from {sourceFilePath} to {destinationFilePath}")

sourceFile = open(sourceFilePath)
sourceData = sourceFile.read()

print(f"The source file is {len(sourceData)} bytes long.")

print(f"Does the destination file exist? {path.exists(destinationFilePath)}")
print("Hit RETURN to continue or CTRL-C to exit.")
input()

destinationFile = open(destinationFilePath, "w")
destinationFile.write(sourceData)

print("Done.")

sourceFile.close()
destinationFile.close()