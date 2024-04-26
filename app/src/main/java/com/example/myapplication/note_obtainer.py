import json

#gets what user wants
team_number = input("Input team number: ")
matchdata = open('C:/Users/camil/Robotics/JoinedData/MergedData.json', 'r')
matches = json.loads(matchdata.read())

def find_nth_occurrence_1(s, sub, n):
	count = s.count(sub)
	if count < n:
		return -1
	else:
		index = -1
		for i in range(n):
			index = s.find(sub, index+1)
		return index

#prints non-physical notes
for match in range(len(matches)):
	current_match = matches[match]
	if current_match["teamNumber"] == team_number:
		print("match" + current_match["matchNumber"] + ":" + current_match["notes"])
	