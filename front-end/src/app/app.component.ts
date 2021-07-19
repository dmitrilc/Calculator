import { Component } from '@angular/core';
//import * as math from 'mathjs';
import {evaluate} from 'mathjs';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'front-end';

  displayText: string = "";

  divide(): void {
    if(!this.endsWithOperator()) this.displayText += "/";
  }

  seven(): void {
    //if(this.endsWithOperator()) 
    this.displayText+= "7";
  }

  eight(): void {
    //if(this.endsWithOperator()) 
    this.displayText+= "8";
  }

  nine(): void {
    //if(this.endsWithOperator()) 
    this.displayText+= "9";
  }

  multiply(): void {
    if(!this.endsWithOperator()) this.displayText+= "*";
  }

  four(): void {
    //if(this.endsWithOperator()) 
    this.displayText+= "4";
  }

  five(): void {
    //if(this.endsWithOperator()) 
    this.displayText+= "5";
  }

  six(): void {
    //if(this.endsWithOperator()) 
    this.displayText+= "6";
  }

  subtract(): void {
    if(!this.endsWithOperator()) this.displayText+= "-";
  }

  one(): void {
    //if(this.endsWithOperator()) 
    this.displayText+= "1";
  }

  two(): void {
    //if(this.endsWithOperator()) 
    this.displayText+= "2";
  }

  three(): void {
    //if(this.endsWithOperator()) 
    this.displayText+= "3";
  }

  add(): void {
    if(!this.endsWithOperator()) this.displayText+= "+";
  }

  negate(): void {
    if (this.displayText.includes(".")) {
      this.displayText = (Number.parseFloat(this.displayText) * -1).toString() ;
    } else {
      this.displayText = (Number.parseInt(this.displayText) * -1).toString() ;
    }
  }

  zero(): void {
    //if(this.endsWithOperator()) 
    this.displayText+= "0";
  }

  dot(): void {
    if(!this.endsWithOperator()) this.displayText+= ".";
  }

  solve(): void {
    const res: any = evaluate(this.displayText);
    this.displayText = res.toString();
  }

  modulo(): void {
    if(!this.endsWithOperator()) this.displayText += "%";
  }

  deleteLast(): void {
    if(this.displayText.length > 1) {
      this.displayText = this.displayText.substr(0, this.displayText.length - 1);
    } else {
      this.reset();
    }
  }

  reset(): void {
    this.displayText = "";
  }

  endsWithOperator(): boolean {
    const lastChar: string = this.displayText.charAt(this.displayText.length - 1);
    switch(lastChar){
      case "+": return true;
      case "-": return true;
      case "*": return true;
      case "/": return true;
      case "%": return true;
      case ".": return true;
      default: return false;
    }
  }
}
