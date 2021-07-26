from flask import Flask, render_template, request
from database import newsFeedDatabase
from info_scrap import *
from news_source import NewsSource
from news_instance import NewsInstance
from random import shuffle

import os
from win32 import win32api
from win32 import win32process
from win32 import win32gui

def callback(hwnd, pid):
  if win32process.GetWindowThreadProcessId(hwnd)[1] == pid:
    # hide window
    win32gui.ShowWindow(hwnd, 0)

app = Flask(__name__)

QUERY = '''SELECT News.title, News.url, News.subtitle, News.newsDate, Source.name
                FROM News
                JOIN Source ON News.source_id == Source.id 
                WHERE %s == News.source_id
                ORDER BY dataAdded DESC
                LIMIT 4;'''

def reloadDB():
    newsList = []
    with newsFeedDatabase() as currFeed:
        currFeed.create_table()
        for news in CNN():
            currFeed.insertON(news)
        for news in UOL():
            currFeed.insertON(news)

        sourceIDs = currFeed.getSourceIDs()
        for id in sourceIDs:
            newsList += currFeed.query(QUERY % (id))

        currFeed.removeOldest('-7 day')

    shuffle(newsList)
    return newsList

@app.route('/', methods=["POST"])
def reload():
    return mainPage()

@app.route('/')
def mainPage():
    newsList = reloadDB()
    return render_template("./news.html", newsList=newsList)

if __name__ == "__main__":
    # find hwnd of parent process, which is the cmd.exe window
    win32gui.EnumWindows(callback, os.getppid())
    app.run(port=5002)
