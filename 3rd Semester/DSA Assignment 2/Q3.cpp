#include<iostream>
#include<fstream>
#include<string>
using namespace std;
class HtmlParser
{
	string* arr;
	int top = -1;
	int size;

public:

	HtmlParser(int s)
	{
		arr = new string[s];
		size = s;
	}
	~HtmlParser()
	{
		delete[] arr;
	}
	void push(string data)
	{
		if (top == size - 1)
			cout << "Lalach Bori Bala hai" << endl;
		else
			arr[++top] = data;
	}
	string pop()
	{
		if (top == -1)
			cout << "Kuch isi tarha uskey khayal bhi zehn sey nikal nahi paty :(" << endl;
		else
			return arr[top--];
	}
	string Top() const
	{
		if (top == -1)
			return "";
		string val = arr[top];
		return val;
	}
	 
	bool commentChecker(string content)
	{
		if (content[0] == '<' && content[1]=='!' && content[2] == '-')
		{
			if (content[3] != '-' || content[content.length() - 1] != '>' || content[content.length() - 2] != '-' || content[content.length() - 3] != '-')
				return 1;
		}
		return 0;
	}

	bool docChecker(string content)
	{
		if (content[0] == '<' && content[1] == '!' && content[2] == 'D')
			return 1;
		return 0;
	}
	bool attributeTag(string content)
	{
		int equalsTo = 0,singleQuotation=0,DoubleQuotation=0;
		if (content[0] == '<' && content[content.length() - 1] == '>')
		{
			for (int i = 1; i < content.length(); i++)
			{
				if (content[i] == '=')
				{
					equalsTo++;
					if (content[i + 1] == '\'') singleQuotation++,i++;
					else if(content[i + 1] == '"') DoubleQuotation++, i++;
					else return 1;
				}
				else if (content[i] == '\'') singleQuotation++, i++;
				else if (content[i] == '"') DoubleQuotation++, i++;
			}
			if (singleQuotation == 0)
			{
				if (DoubleQuotation == equalsTo * 2)
					return 0;
				else return 1;
			}
			else if (DoubleQuotation == 0)
			{
				if (singleQuotation == equalsTo * 2)
					return 0;
				else return 1;
			}
			else if (singleQuotation != 0 && DoubleQuotation != 0 && singleQuotation % 2 == 0 && DoubleQuotation % 2 == 0) return 0;
			else return 1;
				
		}
		return 0;
	}

	bool openCloseTags(string content)
	{
		ifstream read;
		read.open("Test1.txt");
		string comparer = "</",myString="";
		if (content[0] == '<' && content[content.length() - 1] == '>' && content[1] != '/')
		{
			for (int i = 1; i < content.length(); i++)
			{
				comparer += content[i];
			//	cout << comparer<<endl;
			}
			while (!read.eof())
			{
				getline(read, myString);
				if (myString == comparer)
					return 0;
			}
			read.close();
			
			return 1;
		}
		return 0;
	}
};
int main()
{
	ifstream read;
	string content;
	HtmlParser S(100);
	int lineNumber = 0,errors=0;

	read.open("Test.txt");
	while (!read.eof())
	{
		getline(read, content);
		cout << content << endl;
	}
	read.close();
	cout << endl<<endl;

	read.open("Test.txt");
	getline(read, content);
	lineNumber++;
	S.push(content);
	if (S.Top()!=("<!DOCTYPE html>"))
		cout<<"Error at line Number "<<"["<<lineNumber<< "] :"<<" No proper Doctype declaration tag: "<< S.Top() <<endl,errors++;
	
	
	while (!read.eof())
	{
		getline(read,content);
		S.push(content);
		lineNumber++;
		if(S.commentChecker(content))
			cout << "Error at line Number " << "[" << lineNumber << "] :" << " No proper use of comment Tag! : " << S.Top() << endl, errors++;
		else if(S.docChecker(content))
			cout << "Error at line Number " << "[" << lineNumber << "] :" << " Multiple Doctype declaration Tag! : " << S.Top() << endl, errors++;
		else if(S.attributeTag(content))
			cout << "Error at line Number " << "[" << lineNumber << "] :" << " No proper use of attribute Tag! : " << S.Top() << endl, errors++;
		else if (S.openCloseTags(content))
			cout << "Error at line Number " << "[" << lineNumber << "] :" << " No proper opening/closing of Tag! : " << S.Top() << endl, errors++;
	}
	cout << endl<<"Total Number of Errors: " << "[" << errors << "]" << endl;
	read.close();
}