import os

# Specify the directory path
try:
    directory_path = r'C:\Users\camil\Documents\ScoutingData'
except:
    print("directory path is not correct. You (the person running this code) must update it to fit your computer")
    raise NotADirectoryError

# Get a list of all ".json" files in the directory
json_files = [file for file in os.listdir(directory_path) if file.endswith('.json')]

# Initialize an empty array
merged_content = '['

# Separator starts empty for first file
separator = ''

# Read the contents of each ".txt" file and append to the merged content
for json_file in json_files:
    file_path = os.path.join(directory_path, json_file)
    with open(file_path, 'r', encoding='utf-8') as in_file:
        merged_content += separator
        separator = ','
        merged_content += in_file.read()

# Close the array
merged_content += ']'

# Write the merged content to the output file
try:
    output_file_path = r'C:\Users\camil\Robotics\JoinedData\MergedData.json'
except:
    print("see directory path note")
    raise NotADirectoryError
with open(output_file_path, 'w', encoding='utf-8') as outfile:
    outfile.write(merged_content)

print(f"Merged data saved to {output_file_path}")
