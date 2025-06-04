//#include <iostream>
//#include <fstream>
//#include <string>
//using namespace std;
//
//class HtmlParser {
//    string* arr;
//    int top = -1;
//    int size;
//
//public:
//    HtmlParser(int s) {
//        arr = new string[s];
//        size = s;
//    }
//
//    ~HtmlParser() {
//        delete[] arr;
//    }
//
//    void push(const string& data) {
//        if (top == size - 1)
//            cout << "Stack overflow: Cannot push more tags!" << endl;
//        else
//            arr[++top] = data;
//    }
//
//    string pop() {
//        if (top == -1)
//            cout << "Stack underflow: No tags to pop!" << endl;
//        else
//            return arr[top--];
//        return "";
//    }
//
//    string Top() const {
//        if (top == -1)
//            return "";
//        return arr[top];
//    }
//
//    bool isEmpty() const {
//        return top == -1;
//    }
//
//    bool commentChecker(const string& content) {
//        // Check if it starts with <!-- and doesn't end with -->
//        return (content.find("<!--") != string::npos && content.find("-->") == string::npos);
//    }
//
//    bool docChecker(const string& content) {
//        // Check if it starts with <!DOCTYPE (case insensitive)
//        string contentLower = content;
//        for (char& c : contentLower) c = tolower(c);
//        return (contentLower.find("<!doctype") == 0);
//    }
//
//    bool attributeTag(const string& content) {
//        int singleQuote = 0, doubleQuote = 0;
//        if (content[0] == '<' && content[content.length() - 1] == '>') {
//            for (size_t i = 1; i < content.length(); i++) {
//                if (content[i] == '\'') singleQuote++;
//                else if (content[i] == '"') doubleQuote++;
//            }
//            if (singleQuote % 2 != 0 || doubleQuote % 2 != 0) return true;
//        }
//        return false;
//    }
//
//    bool openCloseTags(const string& content) {
//        if (content[0] == '<' && content[content.length() - 1] == '>') {
//            // Self-closing tag check
//            if (content[content.length() - 2] == '/') {
//                return false; // Self-closing tags don't need to be pushed to the stack
//            }
//            // Opening tag (not self-closing)
//            if (content[1] != '/') {
//                string tagName = content.substr(1, content.find(" ") - 1);
//                if (tagName.empty()) tagName = content.substr(1, content.find(">") - 1);
//                push("</" + tagName + ">");
//                return false;
//            }
//            // Closing tag
//            else if (content[1] == '/') {
//                if (isEmpty() || pop() != content) {
//                    return true; // Mismatched or extra closing tag
//                }
//            }
//        }
//        return false;
//    }
//};
//
//int main() {
//    ifstream read;
//    string content;
//    HtmlParser S(100);
//    int lineNumber = 0, errors = 0;
//    bool doctypeFound = false;
//    bool commentOpen = false;
//
//    read.open("Test1.txt");
//    while (getline(read, content)) {
//        lineNumber++;
//
//        // Doctype check
//        if (!doctypeFound && S.docChecker(content)) {
//            if (lineNumber != 1) {
//                cout << "Error at line [" << lineNumber << "]: Doctype must be at the start of the document." << endl;
//                errors++;
//            }
//            doctypeFound = true;
//        }
//        else if (S.docChecker(content) && doctypeFound) {
//            cout << "Error at line [" << lineNumber << "]: Multiple Doctype declarations found." << endl;
//            errors++;
//        }
//
//        // Comment check
//        if (commentOpen || S.commentChecker(content)) {
//            commentOpen = true;
//            if (content.find("-->") != string::npos) {
//                commentOpen = false;
//            }
//            else {
//                cout << "Error at line [" << lineNumber << "]: Unclosed comment tag." << endl;
//                errors++;
//            }
//            continue;
//        }
//
//        // Attribute tag check
//        if (S.attributeTag(content)) {
//            cout << "Error at line [" << lineNumber << "]: Unbalanced attribute quotes." << endl;
//            errors++;
//        }
//
//        // Tag matching check
//        if (S.openCloseTags(content)) {
//            cout << "Error at line [" << lineNumber << "]: Mismatched or unclosed tag." << endl;
//            errors++;
//        }
//    }
//
//    if (!S.isEmpty()) {
//        cout << "Error: Unclosed tags found." << endl;
//        errors++;
//    }
//
//    cout << "Total Number of Errors: [" << errors << "]" << endl;
//    read.close();
//
//    return 0;
//}
