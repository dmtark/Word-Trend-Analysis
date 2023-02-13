'''
Author: Daniel Tarkoff
File: VisualizeWordTrends.py
'''

import argparse # for command line arguments
import numpy as np
import matplotlib.pyplot as plt

class VisualizeWordTrends:

    def __init__(self, filename, year, data = None):
        self.filename = filename
        lines = []
        self.data = None
        self.year = year

    def read_file(self):
        lines = []
        with open(self.filename, 'r') as file:
            for line in file:
                lines.append(line)
        self.data = lines


    def sortData(self):
        words_list = []
        year_frequencies = []
        overall_frequencies = []
        for line in self.data[:][1:]:
            new_line = line.split(",")
            word = new_line[0]
            words_list.append(word)
            frequency = float(new_line[1])
            year_frequencies.append(frequency)
            overall_frequency = float(new_line[2])
            overall_frequencies.append(overall_frequency)

        return (words_list, year_frequencies, overall_frequencies)
        

    def makeGraph(self):

        self.read_file() # read in data from the reddit text file

        (words, year_frequencies, overall_frequencies) = self.sortData()

        x = np.array(words)
        y1 = np.array(year_frequencies)
        y2 = np.array(overall_frequencies)

        # create graph
        fig, ax = plt.subplots()
        ax.bar(x, y1, width=0.4, label = 'Frequency for ' + self.year)
        x_pos = [i + 0.4 for i, _ in enumerate(x)]
        ax.bar(x_pos, y2, width=0.4, label='Frequency Over All Analyzed Years')

        # add labels and title
        plt.xlabel('Word')
        plt.xticks(rotation=90)
        plt.ylabel('Frequency')
        plt.title('Unique Words List')
        plt.legend()

        # show the graph
        plt.show()


def main(args):
    filename = "reddit_data" + args.year + ".txt"
    print(filename)
    test = VisualizeWordTrends(filename, args.year)
    test.makeGraph()

if __name__ == "__main__":
    parser = argparse.ArgumentParser(description= "Visualize Data Trends for Any Year")
    parser.add_argument("year", type=str, help="the year you wish to analyze")
    # parser.add_argument("percentageIncrease", type=str, help="the minimum percentage of increase for word to be shown")
    args = parser.parse_args()
    main(args)