#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>

int main() {
    /*****begin*****/
	int A, B, C, D, E, F;
	for (A = 0; A <= 1; A += 1)
		for (B = 0; B <= 1; B += 1)
			for (C = 0; C <= 1; C += 1)
				for (D = 0; D <= 1; D += 1)
					for (E = 0; E <= 1; E += 1)
						for (F = 0; F <= 1; F += 1)
						{
							if (6 ==
								(A || B)
								+ (!(A && D))
								+ ((A && E) || (A && F) || (E && F))
								+ ((B && C) || (!B && !C))	
								+ ((C && !D) || (D && !C))
								+ (D || (!E)))
							{
								printf("×÷°¸ÈË£º");
								if (A)
									printf("A");
								if (B)
									printf("B");
								if (C)
									printf("C");
								if (D)
									printf("D");
								if (E)
									printf("E");
								if (F)
									printf("F");
							}
						}
	return 0;
    /*****end*****/
}