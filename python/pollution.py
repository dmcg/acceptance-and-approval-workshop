
#import pandas

#def load_history(fname):
#    return pandas.read_csv(fname, index_col='Year')

#def project(history, to_year):
#    new_index = range(history.index[0], to_year+1)
#    return history.reindex(new_index).interpolate(method='spline', order=2)


import csv


class History:
    def __init__(self, rows):
        self.headers = rows[0]
        self.records = rows[1:]
        
    def to_records(self):
        return self.records


def load_history(fname):
    return History(list(csv.reader(open(fname))))


def project(history, to_year):
    return history

