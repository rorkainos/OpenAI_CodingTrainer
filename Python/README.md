# Data Exploration

This module contains functions for exploring data.

## get_top_n_values

This function takes a list of values and an integer n, and returns a sorted list of the top n values.

### Usage

```python
from data_exploration import get_top_n_values

data = [1, 2, 3, 4, 5]
top_n_values = get_top_n_values(data, 3)
print(top_n_values)  # [5, 4, 3]
```

## Testing

To run the unit tests, use the following command:

```bash
python -m unittest discover -s tests
```

### Incorrect Code

The original implementation of `get_top_n_values` did not sort the list of top n values in descending order. This is incorrect because the order of the values is not guaranteed to be the same every time the function is called.

```python
def get_top_n_values(data, n):
    top_n_values = []
    for i in range(n):
        max_value = max(data)
        top_n_values.append(max_value)
        data.remove(max_value)
    return top_n_values
```

### Unit Test for Incorrect Code

The unit test for the incorrect code expects the function to return a list of top n values that is not sorted in descending order.

```python
import unittest
from data_exploration.incorrect_code import get_top_n_values

class TestGetTopNValues(unittest.TestCase):
    def test_get_top_n_values(self):
        data = [1, 2, 3, 4, 5]
        self.assertEqual(get_top_n_values(data, 3), [5, 4, 3])
```

### Corrected Code

The corrected implementation of `get_top_n_values` sorts the list of top n values in descending order before returning it.

```python
def get_top_n_values(data, n):
    top_n_values = []
    for i in range(n):
        max_value = max(data)
        top_n_values.append(max_value)
        data.remove(max_value)
    return sorted(top_n_values, reverse=True)
```

### Unit Test for Corrected Code

The unit test for the corrected code expects the function to return a sorted list of top n values in descending order.

```python
import unittest
from data_exploration.corrected_code import get_top_n_values

class TestGetTopNValues(unittest.TestCase):
    def test_get_top_n_values(self):
        data = [1, 2, 3, 4, 5]
        self.assertEqual(get_top_n_values(data, 3), [5, 4, 3])
```