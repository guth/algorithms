# We put a end=' ' at the end of each print line.
# This tells print to not end the line with a newline character and go to the next line.
print("What is your name?", end=" ")
name = input()
print("How old are you?", end=" ")
age = int(input())

print(f"Your name is {name} and you are age {age}")
print(f"Your age plus 1 is {age+1}")