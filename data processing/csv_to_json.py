import csv, json, datetime

# Specify the directory path
csv_file_name = r'C:\Users\camil\Robotics\JoinedData\PNCMP2024-MatchExport-Q100.csv'
json_file_name = r'C:\Users\camil\Robotics\JoinedData\DCMPQualMatches.json'

all_matches = []

with open(csv_file_name, newline='', encoding='utf-8') as csv_file:
    csv_reader = csv.reader(csv_file)
    first_row = True
    for row in csv_reader:
        if first_row:
            column_names = []
            for column_name in row:
                column_names.append(column_name.strip('\uFEFF'))
            first_row = False
        else:
            current_record = {}
            for i in range(len(column_names)):
                current_record[column_names[i]] = row[i]
            all_matches.append(current_record)
    
with open(json_file_name, 'w', encoding='utf-8') as json_file:
    json_file.write(json.dumps(all_matches, indent=4))