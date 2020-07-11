def print_two(*args):
	arg1, arg2 = args
	print(f"arg1: {arg1}, arg2: {arg2}")

def print_two_again(arg1, arg2):
	print(f"arg1: {arg1}, arg2: {arg2}")

def print_one(arg1):
	print(f"arg1: {arg1}")

def print_none():
	print("Nothing!")

print_two("foo", "bar")
print_two_again("foo1", "bar1")
print_one("Just one!")
print_none()