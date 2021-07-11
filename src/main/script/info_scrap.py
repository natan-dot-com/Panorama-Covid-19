from typing import Iterable
from bs4 import BeautifulSoup
import requests
import re
from news_instance import NewsInstance
from news_source import NewsSource
from datetime import datetime

def CNN() -> Iterable[NewsInstance]:
    SOURCE_NAME = "CNN"
    BASE_URL = "https://www.cnnbrasil.com.br"
    SCRAP_URL = "https://www.cnnbrasil.com.br/tudo-sobre/coronavirus"

    request = requests.get(SCRAP_URL)
    html = BeautifulSoup(request.content, 'html.parser')
    newsList = html.find("div", class_="jsx-3947131330 all-about-news__list").findAll("li")

    regList = []
    for news in newsList:
        try:
            newsTitle = news.h2.text.rstrip()
            newsURL = BASE_URL + news.a['href'].rstrip()
            newsSubtitle = news.a['title'].rstrip()
        except KeyError:
            continue

        newsDate = re.findall("\d+", news.a['href'])
        if newsDate:
            year = int(newsDate[0])
            month = int(newsDate[1])
            day = int(newsDate[2])
        else:
            continue

        CNNSource = NewsSource(SOURCE_NAME, BASE_URL, SCRAP_URL)
        regList.append(NewsInstance(newsTitle, newsURL, newsSubtitle,
                                    datetime(year, month, day), CNNSource))

    return regList

def UOL() -> Iterable[NewsInstance]:
    SOURCE_NAME = "UOL"
    BASE_URL = "https://noticias.uol.com.br/"
    SCRAP_URL = "https://noticias.uol.com.br/coronavirus/"

    request = requests.get(SCRAP_URL)
    html = BeautifulSoup(request.content, 'html.parser')
    newsList = html.find("div", class_="flex-wrap")

    regList = []
    for news in newsList.findAll("div", class_="thumbnails-item"):
        checkValidTag = news.find("h3")
        if checkValidTag:
            try:
                newsTitle = news.find("h3").text.rstrip()
                newsURL = news.a['href'].rstrip()
                newsSubtitle = ""
            except KeyError:
                continue

            newsDate = re.findall("\d+", news.find("time", class_="thumb-date").text)
            if newsDate:
                year = int(newsDate[2])
                month = int(newsDate[1])
                day = int(newsDate[0])
            else:
                continue

            UOLSource = NewsSource(SOURCE_NAME, BASE_URL, SCRAP_URL)
            regList.append(NewsInstance(newsTitle, newsURL, newsSubtitle,
                                        datetime(year, month, day), UOLSource))

    return regList
