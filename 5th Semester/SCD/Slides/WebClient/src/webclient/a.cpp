int square(int num)
{
	return (num * num);
}
int func1(int a, int b)
{
	int c = 1;
	int d = 1;
	int e;
	for (int i = 0; i < a; i++)
	{
		c++;
		while (i < b)
		{
			if ((a == b) || (a > 100))
			{
				d++;
				break;
			}
			else
			{
				d--;
				continue;
			}
		}
		cout << "iteration : " << i << "\n";
	}
	e = square(c) + square(d);
	return e;
}