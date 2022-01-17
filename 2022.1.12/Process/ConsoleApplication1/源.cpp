#include <iostream>
#include <queue>
#include <vector>
#include <algorithm>

using namespace std;

struct Pcb{
	int id;
	int arriveTime;
	int startTime;
	int serverTime;
	int finishTime;
	int prio;
	int wholeTime;
	int waitTime;
	int state;
	bool operator<(const Pcb& p) const
	{
		return this->prio > p.prio;	// 大顶堆
	}
};

struct PcbSort1
{
	bool operator() (const Pcb& a, const Pcb& b)
	{
		return a.arriveTime > b.arriveTime;	// 小顶堆
	}
};

struct PcbSort2
{
	bool operator() (const Pcb& a, const Pcb& b)
	{
		return a.serverTime > b.serverTime;	// 小顶堆
	}
};

struct PcbSort3
{
	bool operator() (const Pcb& a, const Pcb& b)
	{
		if(a.prio!= b.prio)
			return a.prio > b.prio;	// 小顶堆
		else
		{
			return a.arriveTime > b.arriveTime;
		}
	}
};

vector<Pcb> FCFS(vector<Pcb>& process)
{
	priority_queue<Pcb,vector<Pcb>,PcbSort1> list;
	for (int i = 0; i < process.size(); i++)
	{
		list.push(process[i]);
	}
	process.clear();
	int endTime=0;
	while (!list.empty())
	{
		Pcb temp = list.top();
		list.pop();
		temp.startTime = max(endTime, temp.arriveTime);
		temp.finishTime = temp.startTime + temp.serverTime;
		temp.wholeTime = temp.finishTime - temp.arriveTime;
		temp.waitTime = temp.startTime - temp.arriveTime;
		endTime = temp.finishTime;
		process.push_back(temp);
	}
	return process;
}
//写的很麻烦很乱，没有使用引用导致了大量的复制，后续会修改更新
vector<Pcb> SJF(vector<Pcb>& process)
{
	priority_queue<Pcb, vector<Pcb>, PcbSort1> list_Arrive;
	priority_queue<Pcb, vector<Pcb>, PcbSort2> list_Server;
	for (int i = 0; i < process.size(); i++)
	{
		list_Arrive.push(process[i]);
	}
	vector<Pcb> res;
	vector<int> id;
	int endTime;
	while (!list_Arrive.empty())
	{
		if (list_Server.empty()) {
			vector<int>::iterator it = find(id.begin(), id.end(), list_Arrive.top().id);
			while (it!=id.end())
			{
				list_Arrive.pop();
				if (list_Arrive.empty())
					break;
				it = find(id.begin(), id.end(), list_Arrive.top().id);
			}
			if (list_Arrive.empty())
				break;
			Pcb temp = list_Arrive.top();
			list_Arrive.pop();
			temp.startTime = max(endTime, temp.arriveTime);
			temp.finishTime = temp.startTime + temp.serverTime;
			temp.wholeTime = temp.finishTime - temp.arriveTime;
			temp.waitTime = temp.startTime - temp.arriveTime;
			endTime = temp.finishTime;
			res.push_back(temp);
			for (int i = 0; i < process.size(); i++)
			{
				if (process[i].arriveTime <= endTime)
				{
					if (process[i].id == temp.id)
					{
						process[i].arriveTime = INT32_MAX;
					}
					else
					{
						list_Server.push(process[i]);
						process[i].arriveTime = INT32_MAX;
					}
				}
			}
		}
		else
		{
			Pcb temp = list_Server.top();
			list_Server.pop();
			temp.startTime = max(endTime, temp.arriveTime);
			temp.finishTime = temp.startTime + temp.serverTime;
			temp.wholeTime = temp.finishTime - temp.arriveTime;
			temp.waitTime = temp.startTime - temp.arriveTime;
			endTime = temp.finishTime;
			res.push_back(temp);
			id.push_back(temp.id);
		}
	}
	return res;
}

vector<Pcb> NonPri(vector<Pcb>& process)
{
	priority_queue<Pcb, vector<Pcb>, PcbSort1> list_Arrive;
	priority_queue<Pcb, vector<Pcb>, PcbSort3> list_Server;
	for (int i = 0; i < process.size(); i++)
	{
		list_Arrive.push(process[i]);
	}
	vector<Pcb> res;
	vector<int> id;
	int endTime;
	while (!list_Arrive.empty())
	{
		if (list_Server.empty()) {
			vector<int>::iterator it = find(id.begin(), id.end(), list_Arrive.top().id);
			while (it != id.end())
			{
				list_Arrive.pop();
				if (list_Arrive.empty())
					break;
				it = find(id.begin(), id.end(), list_Arrive.top().id);
			}
			if (list_Arrive.empty())
				break;
			Pcb temp = list_Arrive.top();
			list_Arrive.pop();
			temp.startTime = max(endTime, temp.arriveTime);
			temp.finishTime = temp.startTime + temp.serverTime;
			temp.wholeTime = temp.finishTime - temp.arriveTime;
			temp.waitTime = temp.startTime - temp.arriveTime;
			endTime = temp.finishTime;
			res.push_back(temp);
			for (int i = 0; i < process.size(); i++)
			{
				if (process[i].arriveTime <= endTime)
				{
					if (process[i].id == temp.id)
					{
						process[i].arriveTime = INT32_MAX;
					}
					else
					{
						list_Server.push(process[i]);
						process[i].arriveTime = INT32_MAX;
					}
				}
			}
		}
		else
		{
			Pcb temp = list_Server.top();
			list_Server.pop();
			temp.startTime = max(endTime, temp.arriveTime);
			temp.finishTime = temp.startTime + temp.serverTime;
			temp.wholeTime = temp.finishTime - temp.arriveTime;
			temp.waitTime = temp.startTime - temp.arriveTime;
			endTime = temp.finishTime;
			res.push_back(temp);
			id.push_back(temp.id);
		}
	}
	return res;
}

void show(vector<Pcb>& process) {
	cout << "	id		arrive		start		finish		Server		whole		wait" << endl;
	int waitTime = 0;
	for (int i = 0; i < process.size(); i++)
	{
		cout << "	" << process[i].id << "		" << process[i].arriveTime << "		" << process[i].startTime << "		" << process[i].finishTime << "		" << process[i].serverTime
			<< "		" << process[i].wholeTime << "		" << process[i].waitTime << endl;
		waitTime += process[i].waitTime;
	}
	cout << endl;
	cout << "	Average WaitTime:	" << (float)(waitTime)/(float)(process.size());
	cout << endl;
	cout << endl;
}

int main() {
	vector<Pcb> process;
	int num;
	cin >> num;
	for (int i = 0; i < num; i++)
	{
		Pcb temp;
		cin >> temp.id;
		cin >> temp.prio;
		cin >> temp.arriveTime;
		cin >> temp.serverTime;
		process.push_back(temp);
	}
	vector<Pcb> process1 = process;
	vector<Pcb> process2 = process;
	cout << "\n							FCFS					\n" << endl;
	process = FCFS(process);
	show(process);
	cout << "\n							SJF					\n" << endl;
	process1 = SJF(process1);
	show(process1);
	cout << "\n							NonPri					\n" << endl;
	process2 = NonPri(process2);
	show(process2);
	return 0;
}