from flask import Flask, render_template, request
from vaccine_estimator import VaccineEstimator

import os
from win32 import win32api
from win32 import win32process
from win32 import win32gui

def callback(hwnd, pid):
  if win32process.GetWindowThreadProcessId(hwnd)[1] == pid:
    # hide window
    win32gui.ShowWindow(hwnd, 0)

app = Flask(__name__)

@app.route('/')
def index():
	return render_template('./form.html')


@app.route('/', methods=['POST'])
def getvalue():
	try:
		if request.method == 'POST':
			age = request.form['age']
			estado = request.form['state']
			pni = request.form['pni']
			print(age, estado, pni)

			estimation = VaccineEstimator(age, estado, pni)
			estimation.estimate()
			official = str(estimation.getOfficialEstimation())
			unofficial = str(estimation.getUnofficialEstimation())

			return render_template("./estimativa.html", official=official, unofficial=unofficial)
	except:
		return render_template("./form.html")


if __name__ == "__main__":
	# find hwnd of parent process, which is the cmd.exe window
	win32gui.EnumWindows(callback, os.getppid())
	app.run(port=5000)
