# Panorama-Covid-19

## Descrição (PT-BR)

## Description (EN)
Panorama Covid is an app mainly made in Java and Python, which it's main purpose is to inform and educate people about the SarS-CoV-2 (also known as COVID-19) ongoing pandemic. It's also the final project of the Object Oriented Programming Class of University of São Paulo - Brazil (ICMC-USP).

### Structure
The app has a main dashboard, that has three main sections:
- When am I going to be vaccinated?
  - It's a section of the app that uses public APIs that collects official data and predicts when the user is going to be vaccinated.
- Statistics
  - This section basically shows the most important statistics about Covid-19 in Brazil.
- News
  - This feature has a selection of trustable/reowned media content to make the user avoid contact with questionable sources and fake news.

## Como instalar / How to install
### Step 0 - Installing Python requirements

1. Create a virtual environment ([`venv`](https://docs.python.org/3/library/venv.html)) with `virtualenv -p python3 venv`
2. Activate your environment: `$ source venv/bin/activate`
3. Install dependencies: `$ pip install -r PanoramaMaven/src/requirements.txt`
