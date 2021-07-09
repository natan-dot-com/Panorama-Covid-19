from typing import Iterable
from bs4 import BeautifulSoup
import requests
import re
from news_instance import NewsInstance
from datetime import datetime

def CNN() -> Iterable[NewsInstance]:
    baseURL = "https://www.cnnbrasil.com.br"
    scrapURL = "https://www.cnnbrasil.com.br/tudo-sobre/coronavirus"

    request = requests.get(scrapURL)
    html = BeautifulSoup(request.content, 'html.parser')
    newsList = html.find("div", class_="jsx-3947131330 all-about-news__list").findAll("li")

    regList = []
    for news in newsList:
        newsTitle = news.h2.text
        newsURL = baseURL + news.a['href']
        newsSubtitle = news.a['title']

        newsDate = re.findall("\d+", news.a['href'])
        year = int(newsDate[0])
        month = int(newsDate[1])
        day = int(newsDate[2])

        regList.append(NewsInstance(newsTitle, newsURL, newsSubtitle,
                                    datetime(year, month, day), "CNN"))

    return regList

def UOL() -> Iterable[NewsInstance]:
    pass

def G1() -> Iterable[NewsInstance]:
    pass
