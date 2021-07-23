from datetime import datetime
from datetime import timedelta

class NewsInstance:
    ID = -1

    def __init__(self, newsTitle: str, newsURL: str, newsSubtitle: str, newsDate: datetime, newsSource: str):
        self.newsTitle = newsTitle
        self.newsURL = newsURL
        self.newsSubtitle = newsSubtitle
        self.newsDate = newsDate
        self.newsSource = newsSource
        self.dataAdded = datetime.now()

    def __str__(self) -> str:
        resultString = "Titulo da noticia: " + self.newsTitle + \
        "URL da noticia: " + self.newsURL + \
        "Subtitulo da noticia: " + self.newsSubtitle + \
        "Data da noticia: " + self.newsDate.strftime("%d/%m/%y") + \
        "Data adicionado: " + self.dataAdded.strftime("%d/%m/%y") + \
        "-"
        return resultString

    def getNewsID(self) -> int:
        return self.ID

    def getNewsTitle(self) -> str:
        return self.newsTitle

    def getNewsURL(self) -> str:
        return self.newsURL

    def getNewsSubtitle(self) -> str:
        return self.newsSubtitle

    def getNewsDate(self) -> datetime:
        return self.newsDate

    def getNewsSource(self) -> str:
        return self.newsSource

    def getNewsDataAdded(self) -> datetime:
        return self.dataAdded

    def isExpired(self) -> bool:
        return datetime.now() > self.dataAdded + timedelta(days=7)
