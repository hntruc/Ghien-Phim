import time
from textblob import TextBlob
import pandas as pd
import csv

df = pd.read_csv('data.csv', sep=',')

for col in ['Description', 'Genre']:
    for i in range(len(df)):
        time.sleep(0.05)
        s = df.loc[i, col]
        df.loc[i, col] = str(TextBlob(s).translate(to='vi'))
        print(df.loc[i, col])

df.to_csv('data_TV.csv', quoting = csv.QUOTE_NONNUMERIC, encoding = "utf-8-sig")
