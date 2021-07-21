from __future__ import annotations
from datetime import date
import requests
from bs4 import BeautifulSoup
from bs4.element import Tag

# Mensagem padrão
DEFAULT_PROMPT_OFFICIAL = 'Sem dados oficiais'
DEFAULT_PROMPT_UNOFFICIAL = 'Não foi possível encontrar uma estimativa para o seu estado/idade'

class VaccineEstimator:

	def __init__(self, age : str, estado : str, pni : str) -> None:
		self.age = age
		self.estado = estado
		self.pni = (pni == 'Sim')
		self.official = Estimation(prompt=DEFAULT_PROMPT_OFFICIAL)
		self.unofficial = Estimation(prompt=DEFAULT_PROMPT_UNOFFICIAL)

		self.form_data = {'age': age,
						  'state': estado}
		if self.pni:
			self.form_data['pni'] = 'on'
	
	def getOfficialEstimation(self) -> Estimation:
		return self.official
	
	def getUnofficialEstimation(self) -> Estimation:
		return self.unofficial

	def estimate(self) -> None:
		info = self.__requestQuandoVouSerVacinado()
		self.__extractEstimation(info)
	
	def __requestQuandoVouSerVacinado(self) -> Tag:
		URL = 'https://quandovouservacinado.com/'
		response = requests.post(URL, data=self.form_data)
		html = BeautifulSoup(response.content, 'html.parser')
		target = html.find('div', class_='quando-vou-ser-vacinado bg-white rounded-lg shadow p-3 mb-2 text-center')
		return target
	
	def __extractEstimation(self, tag : Tag) -> None:
		self.__extractOfficialEstimation(tag)
		self.__extractUnofficialEstimation(tag)
	
	def __extractOfficialEstimation(self, tag: Tag) -> None:
		'''
		Possíveis situações:
			1. Possui a projeção oficial
				prompt = O estado de São Paulo divulgou uma projeção oficial de vacinação em seu site oficial.
				link = link para a projeção
			2. Nao possui
				prompt = Sem Dados oficiais
				link = '' 
		'''
		alert = tag.find('div', class_='alert alert-info')
		if alert:
			self.official.setPrompt(alert.text.strip())
			self.official.setLink(alert.a['href'])

	def __extractUnofficialEstimation(self, tag : Tag) -> None:
		'''
		Possíveis situações:
			1. Existe o calculo de previsão(ex: sem pni, jovem)
				prompt = X meses e Y dias, link = ''
			2. Provavelmente já está apto para tomar a primeira dose(ex: sem pni, mas idoso)
				prompt = Opa, é provavel que você já pode ser vacinado com a primeira dose! 🎉
				link = ''
			3. É do PNI... Mensagem padrão falando do PNI
				prompt = Opa, a vacinação dos grupos prioritarios conforme o PNI (Programa Nacional de Imunização) parece que já começou, mas a ordem das prioridades e grupos está a cargo dos estados ou municipios. 🎉 
				link = link para publicação técnica do PNI
		'''
		date_block = tag.find('h3')
		
		if 'd-block' in str(date_block): # caso 1
			self.unofficial.setPrompt(date_block.find('strong').text.strip())
		else:
			self.unofficial.setPrompt(date_block.text.strip()) # caso 2
			if 'PNI' in self.unofficial.getPrompt(): # caso 3
				self.unofficial.setLink(date_block.a['href'])
		
class Estimation:
	def __init__(self, prompt:str = '', link:str = '') -> None:
		self.prompt = prompt
		self.link = link
	
	def getPrompt(self) -> str:
		return self.prompt
	
	def getLink(self) -> str:
		return self.link
	
	def setPrompt(self, prompt:str) -> None:
		self.prompt = prompt
	
	def setLink(self, link:str) -> None:
		self.link = link

	def __str__(self) -> str:
		if len(self.link) > 0:
			return self.prompt + '<br>' + ' link: ' + self.link + '\n'
		else:
			return self.prompt + '\n'