import json

#gets what user wants
team_number = input("Input team number: ")
matchdata = open('C:/Users/camil/Robotics/JoinedData/MergedData.json', 'r')
matches = json.loads(matchdata.read())

#prints non-physical notes
for match in range(len(matches)):
	current_match = matches[match]
	if current_match["teamNumber"] == team_number:
		print("match" + current_match["matchNumber"] + ":" + current_match["notes"])
	