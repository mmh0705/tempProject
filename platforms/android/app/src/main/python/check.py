from com.chaquo.python import Python
import pickle
from os.path import dirname, join

def main(text):
  dir = str(Python.getPlatform().getApplication().getFilesDir())

  filename = join(dirname(dir),"Bernoulli")
  with open(filename,'rb') as fin:
    clf = pickle.load(fin)

  filename2 = join(dirname(dir),"Bernoullivect")
  with open(filename2,'rb') as fin2:
    count_vect = pickle.load(fin2)

  check=clf.predict(count_vect.transform([text]))
  check=str(check)
  if check == "[1]":
    return "피싱이 아닙니다."
  else:
    filename = join(dirname(dir),"balanced(22)")
    with open(filename,'rb') as fin:
      clf = pickle.load(fin)

    filename2 = join(dirname(dir),"balanced(22)vect")
    with open(filename2,'rb') as fin2:
      count_vect = pickle.load(fin2)

    check=clf.predict(count_vect.transform([text]))
    check=str(check)

    if check == "[0]":
      return "택배사칭 문자입니다."
    elif check == "[1]":
      return "지인사칭 문자입니다."
    elif check == "[2]":
      return "은행사칭 문자입니다."
    elif check == "[3]":
      return "병원사칭 문자입니다."
    elif check == "[4]":
      return "기관사칭 문자입니다."
    elif check == "[5]":
      return "옥션사칭 문자입니다."
    elif check == "[6]":
      return "단순스팸 문자입니다."