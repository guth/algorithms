from sys import argv

script, fileName = argv

text = open(fileName)

print(f"The filename is: {fileName}")
print("The contents of the file is:")
print(text.read())

print("Type the file name again:")
fileNameAgain = input("> ")

textAgain = open(fileNameAgain)
print(textAgain.read())