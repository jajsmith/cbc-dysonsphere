#Radio-Canada Recommendation System Dataset

This dataset contains user sessions collected from activity on the Radio-Canada website.

Each row represents an instance of a user reading a piece of online content,
identified by its ID and position within the user's read sequence.

##Columns

###sessionId

Anonymized user IDs, e.g. `user-0000001`, `user-242718`

###contentId

Internal ID uniquely identifying the Radio-Canada content. These have not been anonymized, and in
fact, the article content can be accessed using the following URL format:

https://services.radio-canada.ca/hackathon/neuro/v1/news-stories/CONTENT_ID?client_key=31b2bb0e-85ec-4406-9b22-31c93d7e75f9

Ex:
https://services.radio-canada.ca/hackathon/neuro/v1/news-stories/1149671?client_key=31b2bb0e-85ec-4406-9b22-31c93d7e75f9



###eventId

Zero-indexed ID identifying the content's position in the user's read sequence. For example,
an event ID of `2` implies that it's the 3rd article the user has read.

##Files

###train.csv

This is the main file for training, containing all the user read events over a period of time.

###test.csv

This is a file for evaluating your model's performance. It's in the same format as the
training file.

Notes:

- The test events are taken from a time period **after** the training period -- there is no overlap.

- Many of the users in the testing file also appear in the training file, but some do not.

- Some of the content in the test set was published after the end of the training period,
  and thus does not appear in the training set.
